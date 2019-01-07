package com.example.demomvn.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AmlCheckResult {

    private List<String> addressData = new ArrayList<>();
    private List<String> passportData = new ArrayList<>();
    private List<String> personalData = new ArrayList<>();
}
