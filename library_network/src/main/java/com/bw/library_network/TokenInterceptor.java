package com.bw.library_network;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * ─────────────────────────────────────────────────────────────────────────
 * ─████████──████████─██████████████─██████──────────██████─██████████████─
 * ─██░░░░██──██░░░░██─██░░░░░░░░░░██─██░░██████████──██░░██─██░░░░░░░░░░██─
 * ─████░░██──██░░████─██░░██████░░██─██░░░░░░░░░░██──██░░██─██░░██████████─
 * ───██░░░░██░░░░██───██░░██──██░░██─██░░██████░░██──██░░██─██░░██─────────
 * ───████░░░░░░████───██░░██████░░██─██░░██──██░░██──██░░██─██░░██─────────
 * ─────████░░████─────██░░░░░░░░░░██─██░░██──██░░██──██░░██─██░░██──██████─
 * ───────██░░██───────██░░██████░░██─██░░██──██░░██──██░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░██████░░██─██░░██──██░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██░░░░░░░░░░██─██░░██████░░██─
 * ───────██░░██───────██░░██──██░░██─██░░██──██████████░░██─██░░░░░░░░░░██─
 * ───────██████───────██████──██████─██████──────────██████─██████████████─
 * ─────────────────────────────────────────────────────────────────────────
 **/
public class TokenInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        return chain.proceed(chain.request().newBuilder().header("Authorization","bearer o-xROxTO7X1PBEFVivf7XBujpFOHCU0NRchE6jERxKYUp426ITonykpb42Wjc3HiQhdTRnD1Om0fgHuO-JqRRXz0_LGZrCAsUgE5DZ8VJUVX1AIT9AD8uNcH7oyZPJkNz54E-8AFWMyoaAagKfQ8iuNnsToIqVa82wfnHVw4afSLclHQT-dvBSdjfWRLO3QA4M-v8yVset0R1lC_Wq5pDlB2MV724moQMqcIShoN_rbY9pr_bd4tWwZ0b5ZQsMYJJzxKk8FuZE_wU4VGgdBHNY-v30EfVsLTSQvzhXAJO8w").build());
    }
}
