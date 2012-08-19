package de.banapple.graphviz.controller;

import java.io.BufferedWriter;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;

import de.banapple.graphviz.GraphvizProcessor;
import de.banapple.graphviz.controller.commands.Dot2Svg;

@Controller
final class GraphvizController
{
    
    private static final Logger log = LoggerFactory
            .getLogger(GraphvizController.class);
    
    @Autowired    
    private GraphvizProcessor processor;
    
    @RequestMapping(value = "svg", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public final void toSvg(
            @RequestBody
            Dot2Svg body,
            HttpServletResponse response) throws IOException
    {
        log.debug("create svg from dot");
        log.debug("body: {}", body);
        
        String result = processor.toSvg(body.getDot());
        
        BufferedWriter writer = new BufferedWriter(response.getWriter());
        writer.append(result);
        writer.flush();
        
        log.debug("successful");
    }
  
}
