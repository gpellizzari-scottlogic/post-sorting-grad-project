package com.scottlogic.filters;

import com.scottlogic.PostFilter;
import com.scottlogic.UserPost;

import java.time.DateTimeException;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DatePostFilter implements PostFilter {

    private OffsetDateTime startDate;
    private OffsetDateTime endDate;

    public DatePostFilter(OffsetDateTime startDate, OffsetDateTime endDate) {
        if (endDate.isBefore(startDate)) {
            throw new DateTimeException("endDate can't be previous to startDate");
        } else {
            this.startDate = startDate;
            this.endDate = endDate;
        }
    }

    @Override
    public List<UserPost> filter(List<UserPost> inputList) {

        if (inputList == null || inputList.isEmpty()) {
            return new ArrayList<UserPost>();
        }

        return inputList.stream()
                .filter(u -> u.getDateTime().isAfter(startDate) && u.getDateTime().isBefore(endDate))
                .collect(Collectors.toList());
    }

    public OffsetDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(OffsetDateTime startDate) {
        if (startDate.isAfter(endDate)) {
            throw new DateTimeException("Start Date can't come after the end date");
        } else {
            this.startDate = startDate;
        }
    }

    public OffsetDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(OffsetDateTime endDate) {
        if (endDate.isBefore(startDate)) {
            throw new DateTimeException("endDate can't be previous to startDate");
        } else {
            this.endDate = endDate;
        }
    }
}
