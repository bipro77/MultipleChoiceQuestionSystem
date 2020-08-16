package com.fireflies.mcqs.controller;

import com.fireflies.mcqs.dto.McqDto;
import com.fireflies.mcqs.model.Mcq;
import com.fireflies.mcqs.service.McqService;
import com.fireflies.mcqs.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

@Controller
@RequestMapping(value = "/mcq")
public class McqController {

    @Autowired
    private McqService mcqService;

    @Autowired
    private SecurityService securityService;

    @RequestMapping(value = { "/viewAll" }, method = RequestMethod.GET)
    public String viewAll(ModelMap model, HttpServletRequest request) {
        HttpSession session  = request.getSession(false);

//        System.out.println("getAttribute " + session.getAttribute("user") );
//        System.out.println("getAttribute " + session.getAttribute("getUsername"));

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("findLoggedInUserNm "+auth.getName());
//        System.out.println("findLoggedInUser "+auth.getDetails());
//        System.out.println("isAuthenticated "+auth.isAuthenticated());
//        System.out.println("findLoggedInUser "+auth.getPrincipal());
//        System.out.println("findLoggedInUser "+auth.getAuthorities());
//        System.out.println("findLoggedInUser "+auth.getCredentials());
//        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        String name = user.getUsername(); //get logged in username
//        System.out.println("findLoggedInUser2 "+securityService.findLoggedInUser());
//
//        AbstractAuthenticationToken auth = (AbstractAuthenticationToken)
//                SecurityContextHolder.getContext().getAuthentication();
//        UserDetails details = (UserDetails) auth.getDetails();
//        System.out.println("findLoggedInUser "+details);
//        CustomUserDetails myUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
//        String username  =myUserDetails.getUser().getUsername();
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getDetails();
//        System.out.println("findLoggedInUserusername "+myUserDetails.getUsername());
//        System.out.println("findLoggedInUser "+securityService.findLoggedInUser());
////        System.out.println("findLoggedInUserisUserAuthenticated "+securityService.isUserAuthenticated());
////        System.out.println("findLoggedInUserusername "+securityService.findLoggedInUsername());
////
////
////        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
////        if (principal instanceof UserDetails) {
////            String username = ((UserDetails)principal).getUsername();
////            System.out.println(true);
////        } else {
////            String username = principal.toString();
////            System.out.println(false);
////        }

        List<Mcq> mcqList = mcqService.findAll();
        model.addAttribute("mcqList", mcqList);
        return "mcq/viewAllMcq.html";
    }

    @RequestMapping(value = { "/exam" }, method = RequestMethod.GET)
    public String exam( ModelMap model) {
       List<Mcq> mcqList = mcqService.findAll();
        model.addAttribute("mcqList", mcqList);
        return "mcq/examMcq.html";
    }

    @RequestMapping(value = { "/exam/{set}" }, method = RequestMethod.GET)
    public String examBySet( @PathVariable("set") String set, ModelMap model) {
        List<Mcq> mcqList = mcqService.findAllByQuesSet(set);
        Collections.shuffle(mcqList);
        ListIterator<Mcq> iterator = mcqList.listIterator();
        while (iterator.hasNext()) {
            Mcq mcq = iterator.next();
            List<String> options = Arrays.asList(mcq.getOption1()+"", mcq.getOption2()+"", mcq.getOption3()+"", mcq.getOption4()+"");
            Collections.shuffle(options);
            mcq.setOption1(options.get(0)+"");
            mcq.setOption2(options.get(1)+"");
            mcq.setOption3(options.get(2)+"");
            mcq.setOption4(options.get(3)+"");
        }
        model.addAttribute("mcqList", mcqList);
        model.addAttribute("set", set);
        return "mcq/examMcq.html";
    }

    @RequestMapping(value = { "/create","/new" }, method = RequestMethod.GET)
    public String create(ModelMap model) {
        model.addAttribute("formName", "Add New MCQ");
//        model.addAttribute("setList", mcqService.findDistinctQuesSet());
        model.addAttribute("setList",  Arrays.asList("A", "B", "C", "D"));
        return "mcq/formMcq.html";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String registration(@ModelAttribute("mcqDtoResult") McqDto mcqDtoResult, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "index.html";
        }

        int quesCount = 0;
        int correctCount = 0;
        for (McqDto mcqDto:mcqDtoResult.getResultList() ) {
            Mcq mcq = mcqService.findAllById( mcqDto.getId());
            mcqDto.setAnsCorrect(mcq.getAnswer());
            quesCount++;
            if ( mcq.getAnswer().trim().equalsIgnoreCase(mcqDto.getAnsChosen().trim()) ){
                correctCount++;
                mcqDto.setAnsFlag(true);
            } else {
                mcqDto.setAnsFlag(false);
            }
        }
        model.addAttribute("correctCount", correctCount);
        model.addAttribute("quesCount", quesCount);
        model.addAttribute("mcqDtoResult", mcqDtoResult.getResultList());
        return "mcq/viewMcqResult.html";
    }

    @RequestMapping(value = {"/save"}, method = RequestMethod.POST)
    public String save(@ModelAttribute("mcq") Mcq mcq, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            for(ObjectError objectError : bindingResult.getAllErrors()){
                System.out.println("\nMessage : "+objectError.getDefaultMessage()+"\nToString : "+objectError.toString());
            }
            return "formMcq.html";
        }
        if (mcq.getId() == null){ // new Object
//            mcq.setStatus(1);
            mcqService.save(mcq);
        } else { //  Load Old Object
            Mcq mcqCurrent = mcqService.findAllById(mcq.getId());
            mcqCurrent.setQuestion(mcq.getQuestion());
            mcqCurrent.setAnswer(mcq.getAnswer());
            mcqCurrent.setOption1(mcq.getOption1());
            mcqCurrent.setOption2(mcq.getOption2());
            mcqCurrent.setOption3(mcq.getOption3());
            mcqCurrent.setOption4(mcq.getOption4());
            mcqCurrent.setQuesSet(mcq.getQuesSet());
            mcqCurrent.setStatus(mcq.getStatus());
            mcqService.save(mcqCurrent);
        }
//        return "mcq/viewAllMcq.html";  return "redirect:/emp/profile/"+employee.getId();
        return "redirect:/mcq/viewAll";
    }

    @RequestMapping(value = {"/update/{id}"}, method = RequestMethod.GET)
    public String update(@PathVariable("id") String id, ModelMap model,HttpServletRequest request) {
        Mcq mcq = mcqService.findAllById(Integer.parseInt(id));
        model.addAttribute("mcqCurrent", mcq); // Also Edit flag
        model.addAttribute("formName", "Edit MCQ");
//        model.addAttribute("setList", mcqService.findDistinctQuesSet());
        HttpSession session = request.getSession(false);
        session.setAttribute("quesSetList", mcqService.findDistinctQuesSet()); // Update in Session, Set List
        model.addAttribute("setList",  Arrays.asList("A", "B", "C", "D"));
        return "mcq/formMcq.html";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") String id, ModelMap model){
        Mcq mcq = mcqService.findAllById(Integer.parseInt(id));
        mcqService.delete(mcq);
        return "redirect:/mcq/viewAll";
    }

    @RequestMapping(value = {"/statusToggle/{id}"}, method = RequestMethod.GET)
    public String statusToggle(@PathVariable("id") String id, ModelMap model) {
        Mcq mcq = mcqService.findAllById(Integer.parseInt(id));
        if (mcq.getStatus() == 1){
            mcq.setStatus(0);
        } else  if (mcq.getStatus() == 0){
            mcq.setStatus(1);
        }
        mcqService.save(mcq);
        return "redirect:/mcq/viewAll";
    }
}
