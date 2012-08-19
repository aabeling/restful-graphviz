package de.banapple.graphviz.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
final class FooController{
   
//   @Autowired
//   IFooService service;
   
   @RequestMapping( value = "foo",method = RequestMethod.GET )
   @ResponseBody
   public final List< String > getAll(){
//      return service.getAll();
       return Arrays.asList("foo", "bar");
   }
   
//   @RequestMapping( value = "foo/{id}",method = RequestMethod.GET )
//   @ResponseBody
//   public final Foo get( @PathVariable( "id" ) final Long id ){
//      return RestPreconditions.checkNotNull( service.getById( id ) );
//   }
//   
//   @RequestMapping( value = "foo",method = RequestMethod.POST )
//   @ResponseStatus( HttpStatus.CREATED )
//   @ResponseBody
//   public final Long create( @RequestBody final Foo entity ){
//      RestPreconditions.checkNotNullFromRequest( entity );
//      return service.create( entity );
//   }
//   
//   @RequestMapping( value = "foo",method = RequestMethod.PUT )
//   @ResponseStatus( HttpStatus.OK )
//   public final void update( @RequestBody final Foo entity ){
//      RestPreconditions.checkNotNullFromRequest( entity );
//      RestPreconditions.checkNotNull( service.getById( entity.getId() ) );
//      service.update( entity );
//   }
//   
//   @RequestMapping( value = "foo/{id}",method = RequestMethod.DELETE )
//   @ResponseStatus( HttpStatus.OK )
//   public final void delete( @PathVariable( "id" ) final Long id ){
//      service.deleteById( id );
//   }
}
