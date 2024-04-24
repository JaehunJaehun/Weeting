import { recoilPersist } from 'recoil-persist';
import { User } from '@/types/user';
import { atom, selector } from 'recoil';

const { persistAtom } = recoilPersist({
  key: 'sessionStorage',
  storage: sessionStorage,
});

export const userState = atom<User>({
  key: 'userState',
  default: {
    memberId: 0,
    nickname: '',
    score: 1000,
    ranking: null,
  },
  effects_UNSTABLE: [persistAtom],
});
