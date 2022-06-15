package com.uestc.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
class Father {
    int fatherId;
    String fatherName;
    int age;


}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Child {
    int fatherId;
    String childName;
}

@Data
@AllArgsConstructor
@NoArgsConstructor

@ToString
class FatherSonVo {
    int fatherId;
    String fatherName;
    int age;
    String childName;


}

public class LeftJoinTest {
    public static void main(String[] args) {
        List<Father> users = new ArrayList<>();
        users.add(new Father(1, "刘炎", 32));
        users.add(new Father(2, "刘昭龙", 37));
        List<Child> childList = new ArrayList<>();
        childList.add(new Child(1, "刘启禄"));
        childList.add(new Child(1, "刘允禄"));

        childList.add(new Child(2, "刘浩南"));
        childList.add(new Child(2, "刘叶丽"));
        List<FatherSonVo> result = new ArrayList<>();
        users.stream().flatMap(f ->
                childList.stream().filter(child -> f.fatherId == child.fatherId).map(
                        child -> {
                            FatherSonVo fatherSonVo = new FatherSonVo(f.fatherId, f.fatherName, f.age, child.childName);
                            result.add(fatherSonVo);
                            return fatherSonVo;
                        }
                )

        ).collect(Collectors.toList());

        System.out.println(result);

    }
}
