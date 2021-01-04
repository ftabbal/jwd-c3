package com.udacity.jdnd.course3.critter.controller;

import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

// Adapted from
// https://stackoverflow.com/questions/31548780/java-generic-utility-method-to-convert-between-two-types/31550250
public class DTOConverter {

    public <Source, Target> Target convert(Source source, Class<Target> targetType) throws
            RuntimeException
    {
        try {
            Target target = targetType.getDeclaredConstructor().newInstance();
            BeanUtils.copyProperties(source, target);
            return target;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public <Source, Target> List<Target> convertList(List<Source> sourceList, Class<Target> targetType) throws
            RuntimeException
    {
        List<Target> results = new ArrayList<>();
        for (Source source : sourceList) {
            results.add(convert(source, targetType));
        }

        return results;
    }
}
