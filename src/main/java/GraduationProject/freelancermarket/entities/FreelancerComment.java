package GraduationProject.freelancermarket.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "freelancer_comments")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "freelancer" })
@PrimaryKeyJoinColumn(name = "comment_id")
@AllArgsConstructor
@NoArgsConstructor
public class FreelancerComment extends Comment {

	@Column(name = "freelancer_id")
	private int freelancerId;

	@ManyToOne
	@JoinColumn(name = "freelancer_id", insertable = false, updatable = false)
	private Freelancer freelancer;

}
