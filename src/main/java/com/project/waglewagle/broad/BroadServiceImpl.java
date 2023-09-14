package com.project.waglewagle.broad;

import com.project.waglewagle.broad.dto.BroadPostRequest;
import com.project.waglewagle.broad.dto.BroadResponse;
import com.project.waglewagle.broad.dto.BroadStyleDTO;
import com.project.waglewagle.broad.dto.BroadUpdateRequest;
import com.project.waglewagle.global.error.ErrorCode;
import com.project.waglewagle.global.error.exception.EntityNotFoundException;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BroadServiceImpl implements BroadService{

    private final BroadRepository broadRepository;

    private final BroadStyleRepository broadStyleRepository;
    @Override
    public BroadResponse getBroad(Long broadId) {
        Broad broad = broadRepository.findById(broadId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.BROAD_NOT_EXIST));
        return new BroadResponse(broad);
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
        broadRepository.save(broad);
    }

    @Override
    @Transactional
    public BroadResponse changeTitle(Long broadId, BroadUpdateRequest request) {
        Broad broad = broadRepository.findById(broadId).orElseThrow(() -> new EntityNotFoundException(ErrorCode.BROAD_NOT_EXIST));
        broad.updateTitle(request.getTitle());

        return new BroadResponse(broadRepository.save(broad));
    }

    @Override
    @Transactional
    public BroadStyleDTO changeStyle(Long broadStyleId, BroadStyleDTO request) {
        BroadStyle broadStyle = broadStyleRepository.findById(broadStyleId).get();
        broadStyle.update(request);

        return new BroadStyleDTO(broadStyleRepository.save(broadStyle));
    }
}
