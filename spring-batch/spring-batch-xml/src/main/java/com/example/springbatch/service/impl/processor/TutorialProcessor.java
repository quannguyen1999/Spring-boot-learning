package com.example.springbatch.service.impl.processor;

import com.example.springbatch.model.Tutorials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TutorialProcessor implements ItemProcessor<Tutorials, Tutorials> {
    @Override
    public Tutorials process(Tutorials tutorials) throws Exception {
        log.info(tutorials.toString());
        return tutorials;
    }
}
