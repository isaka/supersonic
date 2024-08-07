package com.tencent.supersonic.headless.server.facade.rest;

import com.tencent.supersonic.auth.api.authentication.utils.UserHolder;
import com.tencent.supersonic.headless.api.pojo.request.QueryNLReq;
import com.tencent.supersonic.headless.api.pojo.response.MapResp;
import com.tencent.supersonic.headless.server.facade.service.ChatQueryService;
import com.tencent.supersonic.headless.server.facade.service.RetrieveService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/semantic/query")
@Slf4j
public class ChatQueryApiController {

    @Autowired
    private ChatQueryService chatQueryService;

    @Autowired
    private RetrieveService retrieveService;

    @PostMapping("/chat/search")
    public Object search(@RequestBody QueryNLReq queryNLReq,
                        HttpServletRequest request,
                        HttpServletResponse response) throws Exception {
        queryNLReq.setUser(UserHolder.findUser(request, response));
        return retrieveService.retrieve(queryNLReq);
    }

    @PostMapping("/chat/map")
    public MapResp map(@RequestBody QueryNLReq queryNLReq,
                                  HttpServletRequest request,
                                  HttpServletResponse response) {
        queryNLReq.setUser(UserHolder.findUser(request, response));
        return chatQueryService.performMapping(queryNLReq);
    }

    @PostMapping("/chat/parse")
    public Object parse(@RequestBody QueryNLReq queryNLReq,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        queryNLReq.setUser(UserHolder.findUser(request, response));
        return chatQueryService.performParsing(queryNLReq);
    }

}
