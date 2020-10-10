package com.astrokoala.playbook;

import java.util.List;
import java.util.function.Consumer;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class Greet {
  public void greetPeople(final List<String> people) {
    final Consumer<String> _function = (String it) -> {
      InputOutput.<CharSequence>print(this.sayHello(it));
    };
    people.forEach(_function);
  }
  
  public CharSequence sayHello(final String person) {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Hello ");
    _builder.append(person);
    _builder.append("!");
    _builder.newLineIfNotEmpty();
    return _builder;
  }
}
