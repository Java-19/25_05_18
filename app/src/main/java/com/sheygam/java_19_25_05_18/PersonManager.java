package com.sheygam.java_19_25_05_18;


import java.util.Date;

public class PersonManager {
    private static String[] images = {
    "http://res.cloudinary.com/sheygam/image/upload/v1511387450/avatar20_vfrhu3.png",
    "http://res.cloudinary.com/sheygam/image/upload/v1511387451/avatar19_wjr1lw.png",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387450/avatar18_ei27xh.png",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387449/avatar17_oradvl.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387451/avatar16_fnefk8.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387449/avatar15_uz0l3o.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387445/avatar14_uqukyb.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387444/avatar13_jkldxe.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387445/avatar12_gyftko.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387447/avatar11_r1mjvq.png",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387443/avatar10_l7oums.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387447/avatar9_ohbath.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387443/avatar8_g57alr.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387443/avatar7_mrmnmv.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387442/avatar6_zzncks.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387442/avatar5_avunph.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387451/avatar4_dfb2my.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387442/avatar3_l4wh56.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387442/avatar2_fpvowp.jpg",
            "http://res.cloudinary.com/sheygam/image/upload/v1511387446/avatar1_hgkkkv.jpg"
    };

    public static Person getPerson(int position){
        String name = "Person " + (new Date().getTime()%100);
        String email = name + "@mail.com";
        String url = images[position%images.length];
        return new Person(name,email,url);
    }
}
