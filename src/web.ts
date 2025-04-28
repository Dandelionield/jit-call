import { WebPlugin, PluginListenerHandle } from '@capacitor/core';
import type { JitCallPlugin } from './definitions';

export class JitCallWeb extends WebPlugin implements JitCallPlugin {
	async initialize(): Promise<{ token: string }> {
		throw this.unimplemented('Not implemented on web.');
	}

	async startCall(): Promise<void> {
		throw this.unimplemented('Not implemented on web.');
	}

	override async addListener(
		eventName: string | undefined = 'incomingCall',
		listenerFunc: (data: { meetingId: string; userFrom: string }) => void,
	): Promise<PluginListenerHandle> {
		return super.addListener(eventName, listenerFunc);
	}

	async mockIncomingCall(options: { meetingId: string; userFrom: string }) {
		this.notifyListeners('incomingCall', options);
	}
}