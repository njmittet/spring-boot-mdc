package no.njm

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.MDC
import org.springframework.stereotype.Component

private const val X_REQUEST_ID = "x-request-id"

@Component
class RequestFilter : HttpFilter() {

    override fun doFilter(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            request.getHeader(X_REQUEST_ID)?.let {
                MDC.put(X_REQUEST_ID, it)
                response.setHeader(X_REQUEST_ID, it)
            }
            filterChain.doFilter(request, response)
        } finally {
            MDC.remove(X_REQUEST_ID)
        }
    }
}
