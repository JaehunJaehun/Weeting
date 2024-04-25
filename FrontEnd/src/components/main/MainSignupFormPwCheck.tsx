import styles from '@/styles/main/MainLoginForm.module.css';
import { MainSignupFormPasswordCheckProps } from '@/types/user';

const MainSignupFormPwCheck = ({
  password,
  passwordCheck,
  onPasswordCheckHandler,
}: MainSignupFormPasswordCheckProps) => {
  const isPasswordMatch = () => {
    return passwordCheck === password; // 비밀번호와 비밀번호 재확인이 일치하는지 확인
  };

  return (
    <>
      <div className={styles.Mgb}>
        <label className={styles.Label} htmlFor="passwordCheck">
          pw check
        </label>
        <input
          className={styles.InputBox}
          id="passwordCheck"
          type="password"
          placeholder="비밀번호 재확인"
          value={passwordCheck}
          onChange={onPasswordCheckHandler}
        />
      </div>
      <div className={styles.Container}>
        <div className={styles.Label}></div>
        {!isPasswordMatch() && <div className={styles.Mgl}>비밀번호가 일치하지 않습니다</div>}
      </div>
    </>
  );
};

export default MainSignupFormPwCheck;
