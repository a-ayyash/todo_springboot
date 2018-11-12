package com.hmd.tutorials.tut;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

  @RequestMapping(value = "/")
  public String greeting() {
    return "Ya mar7aba w hala bel shabab";
  }
}
