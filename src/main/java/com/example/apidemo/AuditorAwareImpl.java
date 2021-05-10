package com.example.apidemo;

import org.springframework.beans.factory.support.SecurityContextProvider;
import org.springframework.data.domain.AuditorAware;
//import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Security;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

//        Student st = (Student) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Optional.of("std100");
    }
}
