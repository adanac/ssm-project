package com.feagle.web;

import com.feagle.context.LoginUserCache;
import com.feagle.context.LoginUserHelper;
import com.feagle.context.NativeCache;
import com.feagle.context.UserContext;
import com.feagle.dto.Accordion;
import com.feagle.entity.*;
import com.feagle.enums.WhetherEnum;
import com.feagle.service.RoleService;
import com.feagle.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private NativeCache nativeCache;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "/security/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, String name, String pwd) {
        User user = userService.getUser(name, pwd);
        if (null == user) {
            return "/security/login";
        }
        List<Accordion> accordions = null;
        try {
            if (Objects.equals("admin", user.getUserName())) {
                accordions = new LoginUserHelper().getAccordions(true, user.getId());
            } else {
                List<UserRole> userRoles = userService.getUserRolesByUserId(user.getId());
                if (null == userRoles || 0 == userRoles.size()) {
                    return "/security/login";
                }
                List<Long> roleIds = new ArrayList<Long>();
                for (UserRole ur : userRoles) {
                    roleIds.add(ur.getRoleId());
                }
                List<Role> roles = roleService.getRoles(roleIds);
                nativeCache.setRoles(user.getId(), roles);

                accordions = getAccordions(false, user.getId());

            }

            LoginUserCache.put(user);
            model.addAttribute("accordions", accordions);
            model.addAttribute("username", name);
            LoginUserCache.setAccordions(user.getUserName(), accordions);

            return "/layout/main";
        } catch (Exception e) {
            LoginUserCache.remove(UserContext.getCurrent().getUser().getUserName());
            return "/security/login";
        }
    }

    private List<Accordion> getAccordions(boolean isAdmin, Long userId) {
        Set<String> permissionUrls = new HashSet<>();
        Set<Long> rootFunctionIdSet = new HashSet<>();
        if (!isAdmin) {



            //UserContext.getCurrent() 为null //TODO
            if(null != UserContext.getCurrent()){
                for (Role role : nativeCache.getRoles(UserContext.getCurrent().getUser().getId())) {
                    List<RoleFunction> roleFunctions = roleService.getRoleFunctions(role.getId());
                    for (RoleFunction rf : roleFunctions) {
                        Function func = nativeCache.getFunction(rf.getFunctionId());
                        if (Objects.equals(func.getAccordion(), WhetherEnum.YES.getValue())) {
                            rootFunctionIdSet.add(func.getId());
                        } else {
                            permissionUrls.add(func.getUrl());
                        }
                    }
                }
            }
        }

        Map<Long, Accordion> accordionMap = new HashMap<>();
        List<Accordion> permissionAccordionSet = new LinkedList<>();

        List<Function> functions = nativeCache.getFunctions();

        List<Accordion> rootAccordionSet = new LinkedList<>();
        for (Function function : functions) {
            Accordion accordion = new Accordion(function.getId(), function.getParentId(), function.getName(), function.getUrl(), function.getSerialNum());
            accordionMap.put(function.getId(), accordion);
            if (!isAdmin) {
                if (permissionUrls.contains(function.getUrl())) {
                    permissionAccordionSet.add(accordion);
                }
                if (rootFunctionIdSet.contains(function.getId())) {
                    rootAccordionSet.add(accordion);
                }
            } else {
                permissionAccordionSet.add(accordion);
                if (WhetherEnum.YES.getValue() == function.getAccordion()) {
                    rootAccordionSet.add(accordion);
                }
            }
        }
        Collections.sort(rootAccordionSet);
        Collections.sort(permissionAccordionSet);
        for (Accordion accordion : rootAccordionSet) {
            completeAccordion(permissionAccordionSet, accordion);
        }
        return rootAccordionSet;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        LoginUserCache.remove(UserContext.getCurrent().getUser().getUserName());
        return "/security/login";
    }


    private void completeAccordion(List<Accordion> permissionAccordionSet,
                                   Accordion rootAccordion) {
        for (Accordion accordion : permissionAccordionSet) {
            if (Objects.equals(accordion.getParentId(), rootAccordion.getId())) {
                rootAccordion.getChildren().add(accordion);
            }
        }
    }

}
