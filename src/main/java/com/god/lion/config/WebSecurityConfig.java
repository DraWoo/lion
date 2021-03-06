package com.god.lion.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;//datasource -> DB설정 application.properties를 사용할 수 있게해줌

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //.csrf
                //->사이트 간 요청 위조는 웹사이트 취약점 공격의 하나로,
                // 사용자가 자신의 의지와는 무관하게 공격자가 의도한 행위를 특정 웹사이트에 요청하게 하는 공격을 말한다.
                // 유명 경매 사이트인 옥션에서 발생한 개인정보 유출 사건에서 사용된 공격 방식 중 하나다
                .csrf().disable()//디스에이블 하면 보안체크를 하지않음.
                .authorizeRequests()
                //.antMatchers("/", "/home(첫 화면 로그인페이지 같은 ").permitAll() 누구나 접급할 수 있는 페이지를 설정해준다.
                    .antMatchers("/", "/account/register", "/css/**","/images/**", "/api/**").permitAll()//"/api/**" ->postman으로 테스트를 하기 위한 로그인 없이 요청가능하도록 매핑정보를 넣어줌
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/account/login")
                    .permitAll()
                    .and()
                .logout()
                    .permitAll();
    }
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        /*Authentication 로그인 인증
        Authorization    로그인 권한 인증  */
        //현재 DB 테이블에는 user 테이블에는 사용자 정보, role 테이블에는 권한정보만 있다
        //그래서 두 테이블의 join이 필요하다 다대다 매핑
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select username, password, enabled "   //select username,password,enabled(여백이 중요함 쿼리문 뒷에 여백하나 필수)이유는 그래야 where 절과 붙지 않는다 "  ->반드시 이순서대로 select문을 구성
                        + "from user "
                        + "where username = ?")
                //조인 쿼리가 들어가면 데이터 무결성의 성립이 되지만,성능에 불이익이 단점이다.
                .authoritiesByUsernameQuery("select u.username, r.name "     //이부분 쿼리에 manyToMany 다대다 조인쿼리를 작성해주면된다.
                        + "from user_role ur inner join user u on ur.user_id = u.id "
                        + "inner join role r on ur.role_id = r.id "
                        + "where u.username = ?");//여러테이블을 조인을 했기 때문에 user table에 별칭u를 붙여준다.
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();//유저의 비밀번호를 안전하게 암호화 시켜주는 스프링에서 제공해주는 방법
    }
}
