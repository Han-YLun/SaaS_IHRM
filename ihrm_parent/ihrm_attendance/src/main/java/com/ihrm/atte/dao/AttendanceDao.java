package com.ihrm.atte.dao;

import com.ihrm.domain.atte.entity.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface AttendanceDao extends CrudRepository<Attendance,Long> , JpaRepository<Attendance, Long>, JpaSpecificationExecutor<Attendance> {

}
