package de.banapple.graphviz.configuration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import de.banapple.graphviz.configuration.main.ApplicationConfig;


@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { ApplicationConfig.class },loader = AnnotationConfigContextLoader.class )
public final class SpringTest
{
   
   @Test
   public final void whenSpringContextIsInstantiated_thenNoExceptions(){
      // When
   }
   
}
