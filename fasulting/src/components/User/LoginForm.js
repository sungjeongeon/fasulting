// import * as React from 'react';
// import Button from '@mui/material/Button';
// import { Card } from '@mui/material';
// import CssBaseline from '@mui/material/CssBaseline';
// import TextField from '@mui/material/TextField';
// import FormControlLabel from '@mui/material/FormControlLabel';
// import Checkbox from '@mui/material/Checkbox';
// import Link from '@mui/material/Link';
// import Grid from '@mui/material/Grid';
// import Box from '@mui/material/Box';
// import Typography from '@mui/material/Typography';
// import Container from '@mui/material/Container';
// import Radio from '@mui/material/Radio';
// import RadioGroup from '@mui/material/RadioGroup';
// import FormControl from '@mui/material/FormControl';

// function LoginCard() {
//     return (
//         <Container component="main" maxWidth="xs">
//             <CssBaseline />
//             <Box
//             sx={{
//                 marginTop: 8,
//                 display: 'flex',
//                 flexDirection: 'column',
//                 alignItems: 'center',
//             }}
//             >
//             <Typography component="h1" variant="h4">
//                 로그인
//             </Typography>
//             <Card style={{padding: '40px'}}>
//             <Box component="form" noValidate sx={{ mt: 1 }}>
//                 <FormControl>
//                     <RadioGroup
//                         row
//                         aria-labelledby="demo-row-radio-buttons-group-label"
//                         name="row-radio-buttons-group"
//                     >
//                         <FormControlLabel value="user" control={<Radio />} label="일반 회원" />
//                         <FormControlLabel value="psuser" control={<Radio />} label="병원 회원" />
//                     </RadioGroup>
//                 </FormControl>
//                 <TextField
//                 margin="normal"
//                 required
//                 fullWidth
//                 id="email"
//                 name="email"
//                 label="이메일 주소"
//                 autoComplete="email"
//                 autoFocus/>
//                     <Link href="#" variant="body2">
//                         비밀번호를 잊으셨나요?
//                     </Link>
//                 <TextField
//                 margin="normal"
//                 required
//                 fullWidth
//                 name="password"
//                 label="비밀번호"
//                 type="password"
//                 id="password"
//                 autoComplete="current-password"
//                 />
//                 <FormControlLabel
//                 control={<Checkbox value="remember" color="primary" />}
//                 label="아이디 저장"
//                 />
//                 <Button
//                 type="submit"
//                 fullWidth
//                 variant="contained"
//                 sx={{ mt: 3, mb: 2 }}
//                 style={{color: 'white'}}
//                 >
//                 로그인
//                 </Button>
//                 <Grid container>
//                 <Grid item>
//                     아직 아이디가 없으신가요?<br />
//                     <Link to={"/resister"}>회원가입</Link>
//                 </Grid>
//                 </Grid>
//             </Box>
//             </Card>
//             </Box>
//         </Container>
//   );
// }

// export default LoginCard;

import React from "react";
import ReactDOM from "react-dom";
import { useFormik } from "formik";
import * as yup from "yup";
import {
  Container,
  Box,
  Typography,
  Card,
  FormControl,
  FormControlLabel,
  Radio,
  RadioGroup,
  TextField,
  Button,
  Link,
} from "@mui/material";
import { CssBaseline } from "@mui/material";

const validationSchema = yup.object({
  email: yup
    .string("Enter your email")
    .email("Enter a valid email")
    .required("이메일을 입력해주세요."),
  password: yup
    .string("Enter your password")
    .min(8, "Password should be of minimum 8 characters length")
    .required("패스워드를 입력해주세요."),
});

export default function LoginForm() {
  const formik = useFormik({
    initialValues: {
      email: "",
      password: "",
    },
    validationSchema: validationSchema,
    onSubmit: (values) => {
      alert(JSON.stringify(values, null, 2));
    },
  });

  return (
    <Container component="main" maxWidth="xs">
      <CssBaseline />
      <Box
        sx={{
          marginTop: 8,
          display: "flex",
          flexDirection: "column",
          alignItems: "center",
        }}
      >
        <Typography component="h1" variant="h4">
          로그인
        </Typography>
        <Card style={{ padding: "40px" }}>
          <form onSubmit={formik.handleSubmit}>
            <Box noValidate sx={{ mt: 1 }}>
              <FormControl>
                <RadioGroup
                  row
                  aria-labelledby="demo-row-radio-buttons-group-label"
                  name="row-radio-buttons-group"
                >
                  <FormControlLabel
                    value="user"
                    control={<Radio />}
                    label="일반 회원"
                  />
                  <FormControlLabel
                    value="psuser"
                    control={<Radio />}
                    label="병원 회원"
                  />
                </RadioGroup>
              </FormControl>
              <TextField
                fullWidth
                id="email"
                name="email"
                label="이메일 입력"
                value={formik.values.email}
                onChange={formik.handleChange}
                margin="normal"
                error={formik.touched.email && Boolean(formik.errors.email)}
                helperText={formik.touched.email ? formik.errors.email : ""}
              />
              <Link href="#" variant="body2">
                비밀번호를 잊으셨나요?
              </Link>
              <TextField
                fullWidth
                id="password"
                name="password"
                label="패스워드 입력"
                type="password"
                value={formik.values.password}
                onChange={formik.handleChange}
                margin="normal"
                error={
                  formik.touched.password && Boolean(formik.errors.password)
                }
                helperText={
                  formik.touched.password ? formik.errors.password : ""
                }
              />
              <Button
                color="primary"
                variant="contained"
                fullWidth
                type="submit"
                sx={{ mt: 3, mb: 2 }}
              >
                로그인
              </Button>
              <Box>
                아직 아이디가 없으신가요?
                <br />
                <Link to={"/resister"}>일반 회원가입 | </Link>
                <Link to={"/resister"}> 의사 회원가입</Link>
              </Box>
            </Box>
          </form>
        </Card>
      </Box>
    </Container>
  );
}