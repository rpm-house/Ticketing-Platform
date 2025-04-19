package com.company.ticket.model;

import java.io.Serializable;
import java.util.List;

import com.company.common.config.audit.Auditable;
import com.company.security.model.User;
import com.company.ticket.util.SeatInfoListConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(
    name = "ticket",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"screening_id", "seat_no"}) // ensure no duplicate seats in same screening
    }
)	
public class Booking extends Auditable implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5371027972172753034L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Convert(converter = SeatInfoListConverter.class)
    @Column(columnDefinition = "TEXT", nullable = false)
    private List<SeatInfo> seatInfoList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id", nullable = false)
    private Screening screening;
}
