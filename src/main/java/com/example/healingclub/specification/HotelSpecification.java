package com.example.healingclub.specification;

import com.example.healingclub.dto.request.SearchHotelRequest;
import com.example.healingclub.entity.Hotel;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecification{
   public static Specification<Hotel> getSpesification(SearchHotelRequest request){

       return ((root, query, criteriaBuilder) -> {
           List<Predicate> predicates = new ArrayList<>();

           if (request.getName() != null){
               predicates.add(
                       criteriaBuilder.like(
                               criteriaBuilder.lower(root.get("name")),
                               "%" + request.getName() + "%"

                       )

               );
           }

           if (request.getCity() != null){
               predicates.add(
                       criteriaBuilder.equal(
                               root.get("city"),
                               request.getCity()
                       )
               );
           }

           return query.where(predicates.toArray(new Predicate[]{})).getRestriction();

       });
   }
}
