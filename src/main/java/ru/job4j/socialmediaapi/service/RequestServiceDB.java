package ru.job4j.socialmediaapi.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.socialmediaapi.model.Post;
import ru.job4j.socialmediaapi.model.Request;
import ru.job4j.socialmediaapi.repository.PostRepository;
import ru.job4j.socialmediaapi.repository.RequestRepository;

@Service
@AllArgsConstructor
public class RequestServiceDB implements RequestService {

    private final RequestRepository requestRepository;

    @Override
    public void create(Request request) {
        requestRepository.save(request);
    }

    @Override
    public int update(Request request) {
        return requestRepository.updateStatusRequest(request.getId(), request.getStatus());
    }
}
