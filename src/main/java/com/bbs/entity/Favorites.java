package com.bbs.entity;

import lombok.Data;

@Data
public class Favorites {
   private Long favoritesId;
   private Long userId;
   private String favoritesName;
}
