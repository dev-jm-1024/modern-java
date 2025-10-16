package com.study.ch09;

public class NewsReader implements Observer{

    private String name;

    public NewsReader(){
    }

    public NewsReader(String name){
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received news: " + message);
    }
}
