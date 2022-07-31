package com.example.springbatch.service.impl.writer;

import com.example.springbatch.model.Tutorials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class TutorialWriter  implements ItemWriter<Tutorials> {
    @Override
    public void write(List<? extends Tutorials> list) throws Exception {
        log.info(String.valueOf(list.size()));
    }
}
