package com.solidbeans.call4help.service;

import com.solidbeans.call4help.model.Evidence;

import java.util.List;

public interface EvidenceService {
    List<Evidence> getEvidences(String userId);
}
