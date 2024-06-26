import { IngameUser } from './user';

export interface RoomCreateProps {
  roomName: string;
  roomMode: 'rank' | 'normal';
  roomMaxCnt: number;
  roomPassword: string | null;
}

export interface RoomWaitInfo {
  roomMode: 'rank' | 'normal';
  roomId: string;
  roomName: string;
  roomStatus: 'waiting' | 'allready' | 'wordsetting' | 'wordfinish' | 'start' | 'end';
  roomMaxCnt: number;
  roomPassword: string | null;
  roomUsers: IngameUser[];
}
