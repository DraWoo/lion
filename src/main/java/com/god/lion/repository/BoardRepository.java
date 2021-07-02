package com.god.lion.repository;

import com.god.lion.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

//JpaRepository <model클래스, Auto Increment type>
public interface BoardRepository extends JpaRepository<Board, Long> {

}
