package com.blog_assessment.blog.Types;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum UserTypes implements Serializable {
    AUTHOR, PUBLISHER, GUEST
}
