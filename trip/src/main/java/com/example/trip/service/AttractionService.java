package com.example.trip.service;

import com.example.trip.domain.AttractionDetail;
import com.example.trip.domain.AttractionInfo;
import com.example.trip.repository.AttractionDescriptionRepository;
import com.example.trip.repository.AttractionDetailRepository;
import com.example.trip.repository.AttractionInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AttractionService {

    private final AttractionInfoRepository attractionInfoRepository;
    private final AttractionDescriptionRepository attractionDescriptionRepository;
    private final AttractionDetailRepository attractionDetailRepository;

    public List<AttractionInfo> attractionInfoList() {
        return attractionInfoRepository.findAll();
    }

    public List<AttractionInfo> cat1attractionInfoList(int pageNum){
        List<AttractionDetail> content_id_list = attractionDetailRepository.findByCat1("A01");
        List<AttractionInfo> list = new ArrayList<>();

        for(int i=pageNum*10; i<pageNum*10+10; i++){
            list.add(attractionInfoRepository.findById(content_id_list.get(i).getId()));
        }

        return list;
    }

    public List<AttractionInfo> cat2attractionInfoList(int pageNum){
        List<AttractionDetail> content_id_list = attractionDetailRepository.findByCat1("A02");
        List<AttractionInfo> list = new ArrayList<>();

        for(int i=pageNum*10; i<pageNum*10+10; i++){
            list.add(attractionInfoRepository.findById(content_id_list.get(i).getId()));
        }

        return list;
    }

}
