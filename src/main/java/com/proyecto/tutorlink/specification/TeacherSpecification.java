package com.proyecto.tutorlink.specification;
import com.proyecto.tutorlink.entity.Characteristic;
import com.proyecto.tutorlink.entity.Subject;
import org.springframework.data.jpa.domain.Specification;
import com.proyecto.tutorlink.entity.Teacher;
import javax.persistence.criteria.*;
import java.util.List;



public class TeacherSpecification {

    public static Specification<Teacher> filterBySubjects(List<String> subjectTitles) {
        return (root, query, criteriaBuilder) -> {
            if (subjectTitles == null || subjectTitles.isEmpty()) {
                return criteriaBuilder.conjunction(); // No filter applied
            }
            Join<Teacher, Subject> join = root.join("subject");
            return join.get("title").in(subjectTitles);
        };
    }

    public static Specification<Teacher> filterByCharacteristics(List<Long> characteristicIds) {
        return (root, query, criteriaBuilder) -> {
            if (characteristicIds == null || characteristicIds.isEmpty()) {
                return criteriaBuilder.conjunction(); // No filter applied
            }
            Join<Teacher, Characteristic> join = root.join("characteristics");
            query.groupBy(root.get("id"));
            query.having(criteriaBuilder.equal(criteriaBuilder.count(join.get("id")), characteristicIds.size()));
            return join.get("id").in(characteristicIds);
        };
    }

    public static Specification<Teacher> byFilter(List<String> subjectTitles, List<Long> characteristicIds) {
        return (root, query, criteriaBuilder) -> {
            query.distinct(true);
            Predicate subjectPredicate = filterBySubjects(subjectTitles).toPredicate(root, query, criteriaBuilder);
            Predicate characteristicPredicate = filterByCharacteristics(characteristicIds).toPredicate(root, query, criteriaBuilder);
            return criteriaBuilder.and(subjectPredicate, characteristicPredicate);
        };
    }
//barra busqueda
    public static Specification<Teacher> hasKeyword(String keyword) {
        return (root, query, cb) -> {
            String likePattern = "%" + keyword.toLowerCase() + "%";
            Join<Teacher, Subject> subjectJoin = root.join("subject", JoinType.LEFT);
            Join<Teacher, Characteristic> characteristicJoin = root.join("characteristics", JoinType.LEFT);

            Predicate namePredicate = cb.like(cb.lower(root.get("name")), likePattern);
            Predicate descriptionPredicate = cb.like(cb.lower(root.get("description")), likePattern);
            Predicate subjectPredicate = cb.like(cb.lower(subjectJoin.get("title")), likePattern);
            Predicate characteristicPredicate = cb.like(cb.lower(characteristicJoin.get("name")), likePattern);

            return cb.or(namePredicate, descriptionPredicate, subjectPredicate, characteristicPredicate);
        };
    }
}


