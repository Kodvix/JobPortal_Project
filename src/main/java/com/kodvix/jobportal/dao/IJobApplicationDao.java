package com.kodvix.jobportal.dao;

import com.kodvix.jobportal.dto.JobApplicationsListDTO;
import com.kodvix.jobportal.entities.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJobApplicationDao extends JpaRepository<JobApplication, Long> {

    @Query("SELECT new com.kodvix.jobportal.dto.JobApplicationsListDTO(" +
            "ja.id, " +
            "ja.job.id, " +
            "ja.job.jobTitle, " +
            "ja.coverLetter, " +
            "ja.freelancer.id, " +
            "CONCAT(ja.freelancer.firstName, ' ', ja.freelancer.lastName), " +
            "ja.freelancer.userName, " +
            "ja.jobSeeker.id, " +
            "CONCAT(ja.jobSeeker.firstName, ' ', ja.jobSeeker.lastName), " +
            "ja.jobSeeker.userName, " +
            "ja.appliedDate) " +
            "FROM JobApplication ja " +
            "LEFT JOIN ja.job " +
            "LEFT JOIN ja.freelancer " +
            "LEFT JOIN ja.jobSeeker " +
            "ORDER BY ja.id")
    List<JobApplicationsListDTO> findAllByJobId(@Param("jobId") Long jobId);

    @Query("SELECT new com.kodvix.jobportal.dto.JobApplicationsListDTO( " +
            "j.id, " +
            "j.job.id, " +
            "j.job.jobTitle, " +
            "j.coverLetter, " +
            "f.id, " +
            "CONCAT(f.firstName, ' ', f.lastName), " +
            "f.userName, " +
            "js.id, " +
            "CONCAT(js.firstName, ' ', js.lastName), " +
            "js.userName) " +
            "FROM JobApplication j " +
            "JOIN j.freelancer f " +
            "JOIN j.jobSeeker js")
    List<JobApplicationsListDTO> findAllApps();


    @Query("select new com.kodvix.jobportal.dto.JobApplicationsListDTO(j.id, j.job.id, j.job.jobTitle, j.coverLetter, f.id, CONCAT(f.firstName,' ', f.lastName), f.userName)  from JobApplication j, Freelancer f where f.id=:frId and j.job.id=:jobId")
    List<JobApplicationsListDTO> findByFreelancerId(@Param("jobId") Long jobId, @Param("frId") Long frId);

    @Query("select new com.kodvix.jobportal.dto.JobApplicationsListDTO(j.id, j.job.id, j.job.jobTitle, j.coverLetter, js.id, CONCAT(js.firstName, ' ', js.lastName), js.userName) " +
            "from JobApplication j, JobSeeker js " +
            "where js.id = :jsId and j.job.id = :jobId and j.jobSeeker.id = js.id")
    List<JobApplicationsListDTO> findByJobSeekerId(@Param("jobId") Long jobId, @Param("jsId") Long jsId);

//	@Query("select new com.org.kodvix.freelanceapp.dto.JobApplicationsListDTO(j.id, j.job.id, j.job.jobTitle, j.coverLetter, js.id, CONCAT(js.firstName, ' ', js.lastName), js.userName) " +
//			"from JobApplication j, JobSeeker js " +
//			"where js.id = :jsId and j.job.id = :jobId and j.jobSeeker.id = js.id")
//	List<JobApplicationsListDTO> findByJobSeekerId(@Param("jobId") Long jobId, @Param("jsId") Long jsId);

}

