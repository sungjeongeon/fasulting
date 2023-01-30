package com.fasulting.demo.entity;

import javax.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
//@Builder
@Getter
@DynamicInsert // Apply changed fields only
@DynamicUpdate // Apply changed fields only
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table (name = "reservation_cal")
public class ReservationCalEntity extends BaseEntity {

   	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "seq")
	private Long seq;

   	@Column(name = "year")
	private Integer year;

   	@Column(name = "month")
	private Integer month;

   	@Column(name = "day")
	private Integer day;

	/** 1: 일, ~ 7:토 */
   	@Column(name = "day_of_week")
	private Integer dayOfWeek;

}
