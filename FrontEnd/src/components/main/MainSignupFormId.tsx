import { idCheckApi } from '@/services/userApi';
import styles from '@/styles/main/MainLoginForm.module.css';
import { MainSignupFormIdProps } from '@/types/user';
import { useState } from 'react';
import Swal from 'sweetalert2';

const MainSignupFormId = ({ id, onIdHandler, idPossible, idCheckHandler }: MainSignupFormIdProps) => {
  const [idChecked, setIdChecked] = useState(0);
  
  const handleIdCheck = (e: React.FormEvent) => {
    e.preventDefault();
    // id 중복확인 api call
    idCheckApi({
      account: id,
    })
      .then((data) => {
        console.log('data :', data);
        if (data.dataBody === true) {
          Swal.fire({
            title: "이미 사용중인 아이디입니다",
            icon: "error"
          });
          idCheckHandler(0); // 사용 불가능한 id에 대해 0을 전달
          setIdChecked(1);
        } else {
          Swal.fire({
            title: "사용할 수 있는 아이디입니다",
            icon: "success"
          });
          idCheckHandler(1); // 사용 가능한 id에 대해 1을 전달
          setIdChecked(0);
        }
      })
      .catch(() => {
        Swal.fire({
          title: "다시 시도해주세요",
          icon: "error"
        });
      });
    console.log('id 중복확인 api call');
  };

  return (
    <>
      <div className={styles.Mgb}>
        <label className={`${styles.Label} FontM20`} htmlFor="id">
          id
        </label>
        <input
          className={`${styles.InputBox} FontM20`}
          id="id"
          type="text"
          placeholder="아이디를 입력하세요"
          value={id}
          onChange={onIdHandler}
        />
        <button onClick={handleIdCheck} className={`${styles.checkBtn} FontM20`}>
          중복 확인
        </button>
      </div>
        {(idPossible === 0 && idChecked === 1) ? (
          <div className={styles.Container}>
            <div className={styles.Label}></div>
            <div className={`${styles.SignupAlertText} FontM20`}>이미 존재하는 아이디 입니다</div>
          </div>
        ) : (
          <div className={styles.BeforeContainer}>
            <div className={styles.Label}></div>
          </div>
        )}

    </>
  );
};

export default MainSignupFormId;
