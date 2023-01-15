package de.revaxlabs.backend.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.springframework.data.domain.Pageable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
public class HalResponseService {


    public <T>PagedModel<EntityModel<EntityModel<T>>> toPageResources(Pageable pageable, Supplier<List<T>> getList, Supplier<Integer> getCount){
        return toPageResources(pageable, getList, getCount, EntityModel::of);
    }

    public <T, TEntityModel extends RepresentationModel<TEntityModel>>PagedModel<EntityModel<TEntityModel>>toPageResources(Pageable pageable, Supplier<List<T>> getList, Supplier<Integer> getCount, RepresentationModelAssembler<T, TEntityModel> resourceAssembler){
        PagedResourcesAssembler<TEntityModel> assembler = new PagedResourcesAssembler<>(null, ServletUriComponentsBuilder.fromCurrentRequest().build());
        return assembler.toModel(toPage(pageable, getList, getCount, resourceAssembler));
    }

    private <TEntityModel extends RepresentationModel<TEntityModel>, T> Page<TEntityModel> toPage(Pageable pageable, Supplier<List<T>> getList, Supplier<Integer> getCount, RepresentationModelAssembler<T,TEntityModel> resourceAssembler) {
        var list = getList.get();
        Integer total = getTotal(pageable, getCount, list);

        List<TEntityModel> resources = new ArrayList<>();

        if (list != null) {
            resources = list.stream().map(obj -> toResource(obj, resourceAssembler)).collect(Collectors.toList());
        }

        return getPage(pageable, total, resources);

    }

    private <T, TEntityModel extends RepresentationModel<TEntityModel>> TEntityModel toResource(T obj, RepresentationModelAssembler<T,TEntityModel> resourceAssembler) {
        if(obj != null){
            Assert.notNull(resourceAssembler, "Bitte einen RepresenatinosModelAssembler angeben");
            return resourceAssembler.toModel(obj);
        }
        return null;
    }

    private <T> Integer getTotal(Pageable pageable, Supplier<Integer> getCount, List<T> list) {
        Integer total = null;
        if(getCount != null){
            if(list != null){
                total = list.size();
                if(list.size() < pageable.getPageSize() && pageable.getPageNumber() > 0){
                    total = pageable.getPageNumber() * pageable.getPageSize() + list.size();
                } else if(list.size() == pageable.getPageSize()){
                    Integer val = getCount.get();
                    if(val != null){
                        total = getCount.get();
                    } else {
                        total = 0;
                    }
                }
            } else{
                total = getCount.get();
            }
        }
            return total;
    }

    private <T> Page<T> getPage(Pageable pageable, Integer total, List<T> list){
        Page<T> result;

        if(total != null){
            result = new PageImpl<>(list, pageable, total);
        } else {
            result = new PageImpl<>(list);
        }
        return result;
    }
}
