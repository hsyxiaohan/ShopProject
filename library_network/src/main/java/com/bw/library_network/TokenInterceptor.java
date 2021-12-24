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
        return chain.proceed(chain.request().newBuilder().header("Authorization","bearer t_bmzxPBmefdNTHD_u2h4V73APEOr5ktrqcTYS-oOe97ldjsRu097IGrXZwfYcsm-UBnkCBnv50XvGknCbiD7gy0NGqaoZymrF8B3-J4rWsDZLPwXSrVHJh-lsZewGb8muq_1Id0-3P92B8Ks89K8ge0pAA-GZViTciG_ZNh14TDP2siMCvSihEdDwlLvWulcxt8LPmC8Duz884i5NTCId4Od0xtJbkv9VM4E27PV-vgmBRKD4SfA5kFuZqDp32nTQ0udJFiEPhFGlM9ZHRINqGYDxBfpc0fBG4qBNInTp0").build());
    }
}
