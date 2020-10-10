package com.astrokoala.playbook

import java.util.List

class Greet {
  def greetPeople(List<String> people) {
      people.forEach[
        print(sayHello);
      ]
  }
  
  def sayHello(String person) '''
		Hello «person»!
	'''
}
