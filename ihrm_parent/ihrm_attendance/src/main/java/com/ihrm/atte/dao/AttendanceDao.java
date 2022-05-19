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

    Attendance findByUserIdAndDay(String id, String atteDate);

    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) at0," +
                    "       COUNT(CASE WHEN adt_statu=1 THEN 1 END) at1," +
                    "       COUNT(CASE WHEN adt_statu=2 THEN 1 END) at2," +
                    "       COUNT(CASE WHEN adt_statu=3 THEN 1 END) at3," +
                    "       COUNT(CASE WHEN adt_statu=4 THEN 1 END) at4," +
                    "       COUNT(CASE WHEN adt_statu=8 THEN 1 END) at8," +
                    "       COUNT(CASE WHEN adt_statu=17 THEN 1 END) at17" +
                    "       FROM atte_attendance WHERE  user_id=?1 AND DAY LIKE ?2")
    Map<String,String> statisByUser(String id, String s);
}
