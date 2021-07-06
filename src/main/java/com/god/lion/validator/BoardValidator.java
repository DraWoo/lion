package com.god.lion.validator;

import com.god.lion.model.Board;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

//# Component→사용해주므로써 **Dependency Injection 이란?**
//
//표준을 정의 할 수 있고, 정의된 표준을 바탕으로 같은 설계를 하게 하여줍니다.
//
//**장점이 많지요?**
//
//1. *재사용성을 높여줍니다.*
//2. *테스트에 용이하죠.*
//3. *코드도 단순화 시켜줍니다.*
//4. *종속적이던 코드의 수도 줄여줍니다.*
//5. *왜 사용하는 지 파악하기가 수월합니다. 코드를 읽기 쉬워지는 점이 있습니다.*
//6. *종속성이 감소합니다. 구성 요소의 종속성이 감소하면, 변경에 민감하지 않습니다.*
//7. *결합도(coupling)는 낮추면서 유연성과 확장성은 향상시킬 수 있습니다.*
//8. 객체간의 의존관계를 설정할 수 있습니다.
//9. 객체간의 의존관계를 없애거나 줄일 수 있습니다.

@Component
public class BoardValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Board.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        //형(type) 변환
        Board b = (Board) obj;
        //SpringUtils->를 사용해서 값이 비어있는지 채워져 있는지 확인
        //content(내용)이 비워있는지 확인
        if (StringUtils.isEmpty(b.getContent())){
            errors.rejectValue("content", "key", "내용을 입력해주세요!!!!!");//key값이 없을경우 3번째 파라미터에서 내용을 적어줄 수 있다.
            
        }

    }
}
