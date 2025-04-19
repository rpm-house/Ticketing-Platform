package com.company.common.config.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {

		if (SecurityContextHolder.getContext().getAuthentication() == null) {
			return Optional.of("system"); // or return Optional.empty()
		}
		log.info("Getting auditor: " + SecurityContextHolder.getContext().getAuthentication());
		return Optional.of(SecurityContextHolder.getContext().getAuthentication().getName());
	}

}
