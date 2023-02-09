import React from "react";
import styles from "./DoctorCard.module.css";
import TagIcon from "@mui/icons-material/Tag";

function DoctorCard({ name, main_category, profileImg }) {
  const basicSrc = "../../assets/images/doctorBasic.png"
  // console.log(profileImg)
  return (
    <div className={styles.card}>
      <div style={{height: '50px'}}>
      <img src={profileImg === null ? basicSrc : profileImg} alt="프로필" className={styles.profileImage}/>
      </div>
      <p className={styles.name}>{name}</p>
      <button className={styles.mainCategory}>
        <TagIcon sx={{ fontSize: 12 }} /> {main_category}
      </button>
    </div>
  );
}

export default DoctorCard;
