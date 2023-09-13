package com.project.waglewagle.broad;

import com.project.waglewagle.broad.dto.BroadPostRequest;
import com.project.waglewagle.broad.dto.BroadResponse;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@NoArgsConstructor(force = true)
@Service
public class BroadServiceImpl implements BroadService{

    private final BroadRepository broadRepository;

    private final BroadStyleRepository broadStyleRepository;
    @Override
    public BroadResponse getBroad(Long broadId) {
        return new BroadResponse(broadRepository.findById(broadId).get());
    }

    @Override
    public void postBroad(BroadPostRequest request) {
        BroadStyle broadStyle = broadStyleRepository.save(request.getBroadSytle().toEntity());
        Broad broad = Broad.builder()
                .version(request.getVersion())
                .title(request.getTitle())
                .broadStyle(broadStyle)
                .url(request.getUrl())
                .build();
    }
}
