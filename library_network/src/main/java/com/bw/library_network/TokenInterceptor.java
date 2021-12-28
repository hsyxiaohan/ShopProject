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
        return chain.proceed(chain.request().newBuilder().header("Authorization","bearer VPYfiVGVc-UqPaFUqijosQ_Cp1DU-2oIzskxuGr9zYfu75gPeyINWUni0LN0S3B8_vwQz0VdJNO3wLaRFTSTlc4IG73GJ80Cw7SSA04U7-Rj90ceuPrBuBFNQSR0yPzCFBcnRGSY3gMCo2b13Q-QTeosDDc_SBlivsyri6fqAHB6trRGqSd6Sz0JRGVyF6z04PccU8y76f_Ba_q1PvdTLT2EH3TJ-udu-Znw4OmCPg4Un9-NVPArMnlwlgXaAVgnSDJLP1NgEu91hbYarkYA8BVHbFBIqfidwLq3YCTVWWY").build());
    }
}
