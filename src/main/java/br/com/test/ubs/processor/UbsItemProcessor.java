package br.com.test.ubs.processor;

import br.com.test.ubs.models.Ubs;
import org.springframework.batch.item.ItemProcessor;




public class UbsItemProcessor implements ItemProcessor<Ubs,Ubs> {

    @Override
    public Ubs process(Ubs ubs) throws Exception {
        return ubs;
    }
}
