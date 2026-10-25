package com.skillops.domain8_recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.skillops.domain8_recruitment.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
