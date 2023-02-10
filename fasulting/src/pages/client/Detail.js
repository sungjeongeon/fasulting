// import { useParams } from "react-router-dom";
import ReserveCard from "../../components/Card/ReserveCard";
// import { styled } from "@mui/material/styles";
import Box from "@mui/material/Box";
// import Paper from "@mui/material/Paper";
import Grid from "@mui/material/Unstable_Grid2";
import BackgroundImage from "../../components/Detail/BackgroundImage";
import SimpleInfo from "../../components/Detail/SimpleInfo";
import HospitalInfo from "../../components/Detail/HospitalInfo";
import DoctorCardList from "../../components/Card/DoctorCardList";
import ReviewInfo from "../../components/Detail/ReviewInfo";
import { useEffect } from "react";
import { useState } from "react";
import axios from "axios";
import axiosAPi from "../../api/axiosApi";
import { useParams } from "react-router-dom";
import { useSelector } from "react-redux";

function Detail() {
  const param = useParams();
  // 병원 id로 병원 정보 요청 - 응답 받아서 각 컴포넌트로 전달 (예정)
  const [detailhospital, setDetailhospital] = useState([]);
  const [reshospital, setReshospital] = useState([]);
  const userData = useSelector((state) => state.user);

  useEffect(() => {
    //병원 선택 상세 조회
    axiosAPi
      .get(`/main/ps-detail/${userData.userSeq}/${param.psSeq}`)
      .then((res) => {
        setDetailhospital(res.data.responseObj);
      });
    //병원 예약 테이블 조회
    axiosAPi.get(`/reservation/${param.psSeq}`).then((res) => {
      setReshospital(res.data.responseObj);
    });
  }, []);

  console.log("reshospital", reshospital);
  console.log("detail", detailhospital);
  return (
    <Box sx={{ flexGrow: 1 }}>
      <Grid container spacing={0}>
        {/* 프로필(배경) 이미지 */}
        <BackgroundImage />
        <Grid xs={12} style={{ height: "15rem" }}></Grid>
        <Grid xs={9}>
          <SimpleInfo detailhospital={detailhospital} />
          <hr />
          <HospitalInfo detailhospital={detailhospital} />
          <hr />
          <DoctorCardList detailhospital={detailhospital} />
          <hr />
          <ReviewInfo detailhospital={detailhospital} />
        </Grid>
        <Grid xs={3}>
          <ReserveCard reshospital={reshospital} />
        </Grid>
      </Grid>
    </Box>
  );
}

export default Detail;