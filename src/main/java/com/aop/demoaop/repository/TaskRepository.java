package com.aop.demoaop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aop.demoaop.domain.persist.Task;

/**
 * @author susan.inga
 *
 */
public interface TaskRepository extends JpaRepository<Task, String> {

}
