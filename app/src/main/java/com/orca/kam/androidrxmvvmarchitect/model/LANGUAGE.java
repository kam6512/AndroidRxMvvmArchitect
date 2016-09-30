package com.orca.kam.androidrxmvvmarchitect.model;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Objects;

/**
 * Created by Kang Young Won on 2016-07-27.
 */
public enum LANGUAGE {
    LANGUAGE_ALL("All Language"),
    LANGUAGE_JAVASCRIPT("JavaScript"),
    LANGUAGE_JAVA("Java"),
    LANGUAGE_PYTHON("Python"),
    LANGUAGE_CSS("CSS"),
    LANGUAGE_PHP("PHP"),
    LANGUAGE_RUBY("RUBY"),
    LANGUAGE_CPP("C++"),
    LANGUAGE_C("C"),
    LANGUAGE_SHELL("Shell"),
    LANGUAGE_C_SHARP("C#"),
    LANGUAGE_OBJECTIVE_C("Objective-C"),
    LANGUAGE_R("R"),
    LANGUAGE_VIML("VimL"),
    LANGUAGE_GO("Go"),
    LANGUAGE_PERL("Perl"),
    LANGUAGE_COFFEESCRIPT("CoffeeScript"),
    LANGUAGE_TEX("TeX"),
    LANGUAGE_SWIFT("Swift"),
    LANGUAGE_SCALA("Scala"),
    LANGUAGE_EMACS("Emacs"),
    LANGUAGE_LISP("Lisp"),
    LANGUAGE_HASKELL("Haskell"),
    LANGUAGE_LUA("Lua"),
    LANGUAGE_CLOJURE("Clojure"),
    LANGUAGE_MATLAB("Matlab"),
    LANGUAGE_ARDUINO("Arduino"),
    LANGUAGE_GROOVY("Groovy"),
    LANGUAGE_PUPPET("Puppet"),
    LANGUAGE_RUST("Rust"),
    LANGUAGE_POWERSHELL("PowerShell"),
    LANGUAGE_MAKEFILE("Makefile");

    String languageName;


    LANGUAGE(String languageName) {
        this.languageName = languageName;
    }


    public String getLanguageName() {
        return languageName;
    }


    public static List<String> languageNameList() {
        List<String> list = Lists.newArrayList();
        for (LANGUAGE language : values()) {
            list.add(language.getLanguageName());
        }
        return list;
    }


    public static LANGUAGE getLanguage(String languageName) {
        for (LANGUAGE language : values()) {
            if (Objects.equals(language.getLanguageName(), languageName)) {
                return language;
            }
        }
        return null;
    }
}
