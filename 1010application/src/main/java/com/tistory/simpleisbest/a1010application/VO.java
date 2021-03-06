package com.tistory.simpleisbest.a1010application;

//아이폰이나 안드로이드 처럼 모바일 프로그래밍에서는 데이터를 나타내는 클래스를 만들 때 (C언어에서 배운) 구조체 형태도 많이 만듭니다.
//객체지향의 디자인 패턴을 적용하면 부피가 커지고 복잡해질 수 있기 때문입니다.
public class VO {
    public int icon;
    public String name;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
