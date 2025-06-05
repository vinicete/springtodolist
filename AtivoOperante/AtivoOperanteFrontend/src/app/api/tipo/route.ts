import { NextResponse } from 'next/server';

export async function GET(request: Request) {
    try {
        const token = request.headers.get('cookie')?.split('; ')
            .find(row => row.startsWith('token='))?.split('=')[1];

        const response = await fetch('http://localhost:8080/api/tipo', {
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json',
            }
        });

        if (!response.ok) {
            throw new Error('Failed to fetch tipos');
        }

        const data = await response.json();
        return NextResponse.json(data);
    } catch (error) {
        console.error('Error fetching tipos:', error);
        return NextResponse.json(
            { message: 'Failed to fetch tipos' },
            { status: 500 }
        );
    }
}