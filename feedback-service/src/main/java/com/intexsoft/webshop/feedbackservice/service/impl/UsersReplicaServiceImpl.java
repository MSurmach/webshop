package com.intexsoft.webshop.feedbackservice.service.impl;

import com.intexsoft.webshop.feedbackservice.dto.UsersReplicaDto;
import com.intexsoft.webshop.feedbackservice.mapper.FeedbackApiMapper;
import com.intexsoft.webshop.feedbackservice.model.UsersReplica;
import com.intexsoft.webshop.feedbackservice.repository.UsersReplicaRepository;
import com.intexsoft.webshop.feedbackservice.service.UsersReplicaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersReplicaServiceImpl implements UsersReplicaService {
    private final UsersReplicaRepository usersReplicaRepository;
    private final FeedbackApiMapper feedbackApiMapper;

    @Override
    public UsersReplicaDto createUsersReplica(UsersReplicaDto usersReplicaDto) {
        UsersReplica savedUsersReplica = usersReplicaRepository.save(feedbackApiMapper.toUsersReplica(usersReplicaDto));
        return feedbackApiMapper.toUsersReplicaDto(savedUsersReplica);
    }
}